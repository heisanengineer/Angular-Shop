import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { AccountService } from "../services/account.service";

@Injectable()
export class LoginGuard implements CanActivate {
    constructor(private accountService: AccountService, private router: Router) { }
    canActivate(root: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        let logged = this.accountService.isLoggedIn();
        if (logged) {
            return true;
        }
        else {
            this.router.navigate(["login"]);
            return false;
        }

    }
}