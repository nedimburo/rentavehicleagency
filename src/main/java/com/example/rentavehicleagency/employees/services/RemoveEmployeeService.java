package com.example.rentavehicleagency.employees.services;

import com.example.rentavehicleagency.damages.entities.DamageEntity;
import com.example.rentavehicleagency.damages.services.DamageService;
import com.example.rentavehicleagency.employees.Employee;
import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import com.example.rentavehicleagency.employees.repositories.EmployeeRepository;
import com.example.rentavehicleagency.reports.entities.ReportEntity;
import com.example.rentavehicleagency.reports.services.ReportService;
import com.example.rentavehicleagency.requests.entities.RequestEntity;
import com.example.rentavehicleagency.requests.services.RequestService;
import com.example.rentavehicleagency.users.services.UserService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class RemoveEmployeeService implements Employee {

    private final EmployeeRepository repository;
    private final DamageService damageService;
    private final ReportService reportService;
    private final RequestService requestService;
    private final UserService userService;

    @Transactional
    public ResponseEntity<?> fireEmployee(Long id) {
        EmployeeEntity employeeEntity = repository.findById(id).orElse(null);
        List<DamageEntity> damagesEmployee = damageService.getDamagesByEmployeeId(employeeEntity.getId());
        List<ReportEntity> reportsEmployee = reportService.findReportsByEmployeeId(employeeEntity.getId());
        List<RequestEntity> requestsEmployee = requestService.findRequestsByEmployeeId(employeeEntity.getId());
        for (int i=0; i<damagesEmployee.size(); i++) {
            damageService.deleteDamageById(damagesEmployee.get(i).getId());
        }
        for (int i=0; i<reportsEmployee.size(); i++) {
            reportService.deleteReportById(reportsEmployee.get(i).getId());
        }
        for (int i=0; i<requestsEmployee.size(); i++) {
            requestService.deleteRequestById(requestsEmployee.get(i).getId());
        }
        repository.deleteById(id);
        userService.deleteUserById(employeeEntity.getUserEntity().getId());
        return new ResponseEntity<>("Employee has been succesfully removed.", HttpStatus.OK);
    }
}
