import { Component, OnInit } from '@angular/core';
import { Phones } from './phones';
import { PhonesService } from './phones.service';

@Component({
  selector: 'app-phones',
  templateUrl: './phones.component.html',
  styleUrls: ['./phones.component.css']
})
export class PhonesComponent implements OnInit {

  phones: Phones[];
  constructor(
    private phonesService: PhonesService
  ) { }

  ngOnInit() {
    this.phonesService.getPhones()
      .subscribe((response: any) =>
        this.phones = response);
  }

}
