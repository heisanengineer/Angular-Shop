import { Injectable } from '@angular/core';
import { User } from '../login/user';


@Injectable()
export class AccountService {

  constructor() { }
  loggedIn = false;
  login(user:User):boolean{
    if(user.userName == "Emre" && user.password == "1234"){
      this.loggedIn = true;
      localStorage.setItem("islogged",user.userName);
      return true;
    }
    else{
      return false;
    }
  }

  isLoggedIn(){
    return this.loggedIn;
  }

  logOut(){
    localStorage.removeItem("islogged");
    this.loggedIn = false;
  }
}
