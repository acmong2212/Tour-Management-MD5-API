import { Component, OnInit } from '@angular/core';
import {Tour} from "../../model/tour";
import {TourService} from "../../service/tour.service";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {Category} from "../../model/category";

@Component({
  selector: 'app-tour-delete',
  templateUrl: './tour-delete.component.html',
  styleUrls: ['./tour-delete.component.css']
})
export class TourDeleteComponent implements OnInit {
  id!: number;
  constructor(private tourService: TourService, private activatedRoute: ActivatedRoute, private router: Router) {
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
      this.tour = data;
      console.log(this.tour);
    })
  }

  deleteTour() {
    this.tourService.deleteTour(this.id).subscribe(() => {
      alert("Delete Success!");
      this.router.navigate(['/tour/tour-list']);
    })
  }

}
