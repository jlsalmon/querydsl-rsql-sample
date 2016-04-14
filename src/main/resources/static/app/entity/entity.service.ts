import {Inject, Injectable} from 'angular2/core';
import {Http}               from 'angular2/http';

import {Entity} from './entity';

@Injectable()
export class EntityService {

  constructor(@Inject(Http) private http: Http) {}

  searchEntities(query: string) {
    return this.http.get('http://localhost:8080/entity/search?query=' + query)
      .map(data => <Entity[]> data.json()._embedded.myEntities)
  }
}
