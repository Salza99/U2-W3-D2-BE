package DavideSalzani.U2W3D2BE.devices;

import DavideSalzani.U2W3D2BE.devices.deviceDTO.ChangeStatusInMantainanceOrDismissDTO;
import DavideSalzani.U2W3D2BE.devices.deviceDTO.NewDeviceDTO;
import DavideSalzani.U2W3D2BE.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public long createDevice(@RequestBody @Validated NewDeviceDTO body, BindingResult validation){
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }else {
            return deviceService.createDevice(body);
        }
    }
    @GetMapping("")
    public Page<Device> getPagesOfDevices(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "deviceStatus") String orderBy){
        return deviceService.getAll(page, size, orderBy);
    }
    @GetMapping("{id}")
    public Device getSingleDevice(@PathVariable("id") long id){
        return deviceService.getSingle(id);
    }
    @PatchMapping("/status/{id}")
    public Device patchDismissOrMaintenance(@RequestBody @Validated ChangeStatusInMantainanceOrDismissDTO body,BindingResult validation, @PathVariable("id") long id){
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }else{
            return deviceService.underMaintenanceOrDismiss(body, id);
        }
    }
    @PatchMapping("/assign/{deviceId}/{userId}")
    public Device patchChangeOwner(@PathVariable("deviceId") long deviceId, @PathVariable("userId") long userId){
        return deviceService.changeAssignedToUser(deviceId, userId);
    }
    @PatchMapping("/available/{id}")
    public Device turnAvailableDevice(@PathVariable("id") long id){
        return deviceService.turnAvailableDevice(id);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeDevice(@PathVariable("id") long id){
        deviceService.removeDevice(id);
    }
}
