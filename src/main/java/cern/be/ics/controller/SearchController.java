package cern.be.ics.controller;

import cern.be.ics.entity.MyEntity;
import cern.be.ics.entity.MyEntityRepository;
import cern.be.ics.predicate.RsqlExpressionBuilder;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Justin Lewis Salmon
 */
@Controller
public class SearchController {

  @Autowired
  private MyEntityRepository entityRepository;

  @RequestMapping(value = "/entity/search", method = GET, produces = "application/json")
  HttpEntity<PagedResources<MyEntity>> search(@RequestParam("query") String query, Pageable pageable, PagedResourcesAssembler assembler) {
    Page<MyEntity> entities;

    if (!query.isEmpty()) {
      Predicate predicate = new RsqlExpressionBuilder<>(MyEntity.class).createExpression(query);
      entities = entityRepository.findAll(predicate, pageable);
    } else {
      entities = entityRepository.findAll(pageable);
    }

    return new ResponseEntity<>(assembler.toResource(entities), HttpStatus.OK);
  }
}
