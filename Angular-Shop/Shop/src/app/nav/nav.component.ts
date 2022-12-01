import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { faHouseDamage, faPlus, faSearch, faUser, faCartPlus } from '@fortawesome/free-solid-svg-icons';
import { SearchBarService } from '../services/search-bar.service';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css'],

})
export class NavComponent {
  navbarSearchText: string = ""
  constructor(
    private searchBar: SearchBarService,
    private accountService: AccountService
  ) { }

  faHouseDamage = faHouseDamage;
  faPlus = faPlus;
  faSearch = faSearch;
  faUser = faUser;
  faCartPlus = faCartPlus;

  getSearchText(event: any) {
    let searchText = String(event.value)
    this.searchBar.navbarSearchText.next(searchText)
  }

  getColor() {
    return 'green';
  }

  isLoggedin(){
    return this.accountService.isLoggedIn();
  }

  logOut(){
    this.accountService.logOut();
  }
}


