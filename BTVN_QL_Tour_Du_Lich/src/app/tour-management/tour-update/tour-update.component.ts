import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Tour} from "../model/tour";
import {TourService} from "../../service/tour.service";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {Category} from "../model/category";
import {CategoryService} from "../../service/category.service";
import {finalize} from "rxjs";
import {AngularFireStorage} from "@angular/fire/compat/storage";

@Component({
  selector: 'app-tour-update',
  templateUrl: './tour-update.component.html',
  styleUrls: ['./tour-update.component.css']
})
export class TourUpdateComponent implements OnInit {
  categories!: Category[];
  category!: Category

  tour: Tour = new Tour(0, '', '', 0, '', this.category);

  id!: number;

  constructor(private tourService: TourService, private activatedRoute: ActivatedRoute,
              private router: Router, private CategoryService: CategoryService,
              private storage: AngularFireStorage) {
    this.activatedRoute.paramMap.subscribe((paramMap: ParamMap) => {
      this.id = Number(<string>paramMap.get('id'))
      this.tourDetail();
      this.findAllCategories();
    });
  }

  ngOnInit(): void {

  }

  tourDetail() {
    this.tourService.findTourById(this.id).subscribe(data => {
      this.tour = data;
      console.log(this.tour);
    })
  }

  findAllCategories() {
    this.CategoryService.findAllCategories().subscribe(data => {
      this.categories = data;
      console.log(this.categories)
    })
  }

  editTour() {
    this.tourService.updateTour(this.tour).subscribe(() => {
      alert('Update Success!');
      this.router.navigate(['/tour/tour-list'])
    })
  }

  //upload File
  @ViewChild('uploadFile', {static: true})
  public imageDom: ElementRef | undefined;

  selectedImage: any | null;
  // arrayPicture = '';

  submit() {
    if (this.selectedImage != null) {
      const filePath = this.selectedImage.name;
      const fileRef = this.storage.ref(filePath);
      this.storage.upload(filePath, this.selectedImage).snapshotChanges().pipe(
        finalize(() => (fileRef.getDownloadURL().subscribe(url => {
          // this.arrayPicture = url;
          this.tour.image = url;
          console.log(url);
        })))
      ).subscribe();
    }
  }

  uploadFileImg() {
    this.selectedImage = this.imageDom?.nativeElement.files[0];
    this.submit();
  }
}
