package fi.roskisprojekti.application.service.task;

import fi.roskisprojekti.application.port.in.broker.DomainEventSubscriber;
import fi.roskisprojekti.application.port.in.task.ManageTasksUseCase;
import fi.roskisprojekti.application.port.out.persistence.TaskRepository;
import fi.roskisprojekti.domain.entity.task.Task;
import fi.roskisprojekti.domain.event.bin.BinDomainEvent;
import fi.roskisprojekti.domain.event.bin.BinEmptyingRequiredEvent;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ManageTasksService implements ManageTasksUseCase {
    private final TaskRepository taskRepository;
    //private final DomainEventSubscriber eventSubscriber;

    /**
    @PostConstruct
    public void init() {
        eventSubscriber.subscribeToBinDomainEvents(this::onBinEvent);
    }

    private void onBinEvent(BinDomainEvent event) {
        if (event instanceof BinEmptyingRequiredEvent e) {
            taskRepository.save(Task.createNew()));
        }
    }
    */
    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

}
