package pl.lodz.p.edu.carpooling.module.service.carpooling.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.carpooling.exception.vehicle.VehicleException;
import pl.lodz.p.edu.carpooling.module.service.carpooling.request.CreateVehicleRequest;
import pl.lodz.p.edu.carpooling.module.service.carpooling.request.UpdateVehicleRequest;
import pl.lodz.p.edu.carpooling.module.service.carpooling.response.GetVehicleDetailsToUpdateResponse;
import pl.lodz.p.edu.carpooling.module.service.carpooling.response.model.VehicleDetailsForList;
import pl.lodz.p.edu.carpooling.module.service.carpooling.service.converter.VehicleToVehicleDetailsForListConverter;
import pl.lodz.p.edu.carpooling.persistence.entity.Account;
import pl.lodz.p.edu.carpooling.persistence.entity.Vehicle;
import pl.lodz.p.edu.carpooling.persistence.repository.AccountRepository;
import pl.lodz.p.edu.carpooling.persistence.repository.VehicleRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(value = Transactional.TxType.REQUIRES_NEW)
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final AccountRepository accountRepository;


    public void createVehicle(CreateVehicleRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findByLogin(authentication.getName());
        Vehicle vehicle = Vehicle.builder()
                .brand(request.getBrand())
                .model(request.getModel())
                .productionYear(request.getProductionYear())
                .numberOfSeats(request.getNumberOfSeats())
                .description(request.getDescription())
                .owner(account)
                .build();
        vehicleRepository.save(vehicle);
    }

    public List<VehicleDetailsForList> getVehiclesAssignedToAuthenticatedAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return vehicleRepository.findByAccountLogin(authentication.getName()).stream()
                .map(VehicleToVehicleDetailsForListConverter::convert)
                .collect(Collectors.toList());
    }

    public GetVehicleDetailsToUpdateResponse getVehicleDetails(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Vehicle vehicle = vehicleRepository.findById(id);
        if (!authentication.getName().equals(vehicle.getOwner().getLogin())) {
            throw VehicleException.vehicleIsNotConnectedWithAccountException();
        }
        return GetVehicleDetailsToUpdateResponse.builder()
                .id(vehicle.getId().toString())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .description(vehicle.getDescription())
                .numberOfSeats(vehicle.getNumberOfSeats())
                .productionYear(vehicle.getProductionYear())
                .version(vehicle.getVersion())
                .build();
    }

    public void updateVehicle(UpdateVehicleRequest request) {
        Vehicle vehicle = vehicleRepository.findById(Long.valueOf(request.getId()));

        vehicle.setBrand(request.getBrand());
        vehicle.setModel(request.getModel());
        vehicle.setDescription(request.getDescription());
        vehicle.setNumberOfSeats(request.getNumberOfSeats());
        vehicle.setProductionYear(request.getProductionYear());
        vehicle.setVersion(request.getVersion());
        vehicleRepository.update(vehicle);
    }

    public void deleteVehicle(String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Vehicle vehicle = vehicleRepository.findById(Long.parseLong(id));
        if (!authentication.getName().equals(vehicle.getOwner().getLogin())) {
            throw VehicleException.vehicleIsNotConnectedWithAccountException();
        }

        vehicleRepository.delete(vehicle);
    }

}
