import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login/login.service';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user.model';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {
  
  user = new User();
  constructor(private router : Router) { 

  }

  ngOnInit(): void {
    window.sessionStorage.setItem("userdetails",null);
    window.sessionStorage.setItem("XSRF-TOKEN",null);
    this.router.navigate(['/login']);
  }


}
