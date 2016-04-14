import 'rxjs/Rx';
import {bootstrap}      from 'angular2/platform/browser';
import {HTTP_PROVIDERS} from "angular2/http";

import {AppComponent}   from './app.component';
import {EntityService}  from './entity/entity.service';

bootstrap(AppComponent, [HTTP_PROVIDERS, EntityService]);
