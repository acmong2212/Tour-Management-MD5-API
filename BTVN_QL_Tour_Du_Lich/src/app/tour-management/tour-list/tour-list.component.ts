import { Component, OnInit } from '@angular/core';
import {Tour} from "../../model/tour";
import {TourService} from "../../service/tour.service";
import {Category} from "../../model/category";

@Component({
  selector: 'app-tour-list',
  templateUrl: './tour-list.component.html',
  styleUrls: ['./tour-list.component.css']
})
export class TourListComponent implements OnInit {
  tourList: Tour[] = [];
  categories: Category[] = [];

  constructor(private tourService: TourService) {
    this.findAllTour();
  }

  ngOnInit(): void {
  }

  findAllTour() {
    this.tourService.findAll().subscribe(data => {
      this.tourList = data;
    })
  }

  searchTour() {
    // @ts-ignore
    let search  = document.getElementById("search-input").value;
    this.tourService.findTourByNameContaining(search).subscribe(data => {
      this.tourList = data;
    })
  }
}
