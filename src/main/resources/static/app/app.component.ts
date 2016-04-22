import {Component, Inject} from 'angular2/core';

import {EntityService}    from './entity/entity.service';
import {Entity}           from './entity/entity';

@Component({
  selector: 'my-app',
  templateUrl: 'app/app.component.html',
  providers: [EntityService]
})
export class AppComponent {

  query: string;
  results: Entity[];
  error: any;

  constructor(@Inject(EntityService) private entityService : EntityService) {
    this.query = 'name == \'entity 1\'';
  }

  search() {
    this.entityService.searchEntities(this.query).subscribe(results => {
      this.results = results;
      this.error = null;
    },

    error => {
      this.error = error._body;
    });
  }
}
