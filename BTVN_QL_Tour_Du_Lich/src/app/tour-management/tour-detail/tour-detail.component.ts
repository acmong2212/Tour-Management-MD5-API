import {Component, OnInit} from '@angular/core';
import {Tour} from "../model/tour";
import {TourService} from "../../service/tour.service";
import {ActivatedRoute, ParamMap, Params} from "@angular/router";
import {Category} from "../model/category";

@Component({
  selector: 'app-tour-detail',
  templateUrl: './tour-detail.component.html',
  styleUrls: ['./tour-detail.component.css']
})
export class TourDetailComponent implements OnInit {

  id!: number;
  constructor(private tourService: TourService, private activatedRoute: ActivatedRoute) {
    this.activatedRoute.paramMap.subscribe((paramMap: ParamMap) => {
        this.id = Number(<string>paramMap.get('id'))
      this.tourDetail()
    });
  }

  ngOnInit(): void {

  }

  tour: Tour = new Tour(0, '','' ,0, '', new Category(0, ''));

  tourDetail() {
    this.tourService.findTourById(this.id).subscribe(data => {
      this.tour = data
      console.log(this.tour)
    })
  }

}
