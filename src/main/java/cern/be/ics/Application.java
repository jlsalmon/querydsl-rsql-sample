package cern.be.ics;

import cern.be.ics.entity.MyEntity;
import cern.be.ics.entity.MyEntityRepository;
import cern.be.ics.entity.NestedEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author Justin Lewis Salmon
 */
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(Application.class, args);

    MyEntityRepository entityRepository = context.getBean(MyEntityRepository.class);

    // Insert some dummy data
    entityRepository.save(new MyEntity(1L, "entity 1", new NestedEntity("nested entity 1")));
    entityRepository.save(new MyEntity(2L, "entity 2", new NestedEntity("nested entity 2")));
  }
}
