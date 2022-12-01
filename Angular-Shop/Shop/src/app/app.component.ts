import { Component } from '@angular/core';
import { faHouseDamage, faPlus, faSearch, faUser } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  faHouseDamage = faHouseDamage;
  faPlus = faPlus;
  faSearch = faSearch;
  faUser = faUser;
}
