package com.gt.gestion_taches.mappers;

import com.gt.gestion_taches.dtos.*;
import com.gt.gestion_taches.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MapperServiceImpl {

    public UserAccountDTO fromUserAccount(UserAccount userAccount) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        BeanUtils.copyProperties(userAccount, userAccountDTO);
        return userAccountDTO;
    }

    public UserAccount fromUserAccountDTO(UserAccountDTO userAccountDTO) {
        UserAccount userAccount = new UserAccount();
        BeanUtils.copyProperties(userAccountDTO, userAccount);
        return userAccount;
    }

    public AdminDTO fromAdmin(Admin admin) {
        AdminDTO adminDTO = new AdminDTO();
        BeanUtils.copyProperties(admin, adminDTO);
        adminDTO.setUserAccountDTO(fromUserAccount(admin.getUserAccount()));
        return adminDTO;
    }

    public DivisionDTO fromDivision(Division division) {
        DivisionDTO divisionDTO = new DivisionDTO();
        BeanUtils.copyProperties(division, divisionDTO);
        divisionDTO.setUserAccountDTO(fromUserAccount(division.getUserAccount()));
        return divisionDTO;
    }

    public Division fromDivisionDTO(DivisionDTO divisionDTO) {
        Division division = new Division();
        BeanUtils.copyProperties(divisionDTO, division);
        division.setUserAccount(fromUserAccountDTO(divisionDTO.getUserAccountDTO()));
        return division;
    }

    public ResponsibleDTO fromResponsible(Responsible responsible) {
        ResponsibleDTO responsibleDTO = new ResponsibleDTO();
        BeanUtils.copyProperties(responsible, responsibleDTO);
        responsibleDTO.setUserAccountDTO(fromUserAccount(responsible.getUserAccount()));
        return responsibleDTO;
    }

    public Responsible fromResponsibleDTO(ResponsibleDTO responsibleDTO) {
        Responsible responsible = new Responsible();
        BeanUtils.copyProperties(responsibleDTO, responsible);
        responsible.setUserAccount(fromUserAccountDTO(responsibleDTO.getUserAccountDTO()));
        return responsible;
    }

    public SecretaryDTO fromSecretary(Secretary secretary) {
        SecretaryDTO secretaryDTO = new SecretaryDTO();
        BeanUtils.copyProperties(secretary, secretaryDTO);
        secretaryDTO.setUserAccountDTO(fromUserAccount(secretary.getUserAccount()));
        return secretaryDTO;
    }

    public Secretary fromSecretaryDTO(SecretaryDTO secretaryDTO) {
        Secretary secretary = new Secretary();
        BeanUtils.copyProperties(secretaryDTO, secretary);
        secretary.setUserAccount(fromUserAccountDTO(secretaryDTO.getUserAccountDTO()));
        return secretary;
    }

    public AgendaDTO fromAgenda(Agenda agenda) {
        AgendaDTO agendaDTO = new AgendaDTO();
        BeanUtils.copyProperties(agenda, agendaDTO);
        agendaDTO.setIdResponsible(agenda.getResponsible().getId());
        return agendaDTO;
    }

    public Agenda fromAgendaDTO(AgendaDTO agendaDTO) {
        Agenda agenda = new Agenda();
        BeanUtils.copyProperties(agendaDTO, agenda);
        return agenda;
    }

    public TaskDTO fromTask(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        BeanUtils.copyProperties(task, taskDTO);
        taskDTO.setDivisionId(task.getDivision().getId());
        taskDTO.setNameDivision(task.getDivision().getNameDivision());
        return taskDTO;
    }

    public Task fromTaskDTO(TaskDTO taskDTO) {
        Task task = new Task();
        BeanUtils.copyProperties(taskDTO, task);
        return task;
    }
}
