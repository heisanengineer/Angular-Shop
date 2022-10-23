import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoggingService {
  messages: string[] = [];

  add(message:String){
    this.messages.push();
  }

  clear(): void{
    this.messages = []
  }
  constructor() { }
}
