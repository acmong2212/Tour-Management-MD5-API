import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Tour} from "../model/tour";
import {TourService} from "../../service/tour.service";
import {Router} from "@angular/router";
import {Category} from "../model/category";
import {CategoryService} from "../../service/category.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AngularFireStorage} from "@angular/fire/compat/storage";
import {finalize} from "rxjs";

@Component({
  selector: 'app-tour-create',
  templateUrl: './tour-create.component.html',
  styleUrls: ['./tour-create.component.css']
})
export class TourCreateComponent implements OnInit {
  tourList!: Tour[];
  categories!: Category[];
  category!: Category
  formCreate!:FormGroup;

  tour: Tour = new Tour(0, '','' ,0, '', this.category);

  constructor(private tourService: TourService, private router: Router,
              private CategoryService: CategoryService, private storage: AngularFireStorage) {
    this.findAllCategories();
  }

  ngOnInit(): void {
    this.formCreate = new FormGroup({
      name: new FormControl('', Validators.maxLength(6)),
      image: new FormControl(''),
      price: new FormControl(0, Validators.pattern('[0-4]')),
      description: new FormControl("",Validators.required),
      category: new FormControl(new Category(1,''))
    })
  }

  createTour() {
    this.tourService.createTour(this.formCreate.value).subscribe(() => {
      alert("Create Success!")
      this.router.navigate(['/tour/tour-list'])
    })
  }

  findAllCategories() {
    this.CategoryService.findAllCategories().subscribe(data => {
      this.categories = data;
    })
  }

  //upload File
  @ViewChild('uploadFile', {static: true})
  public imageDom: ElementRef | undefined;

  selectedImage: any | null;
  arrayPicture = '';

  submit() {
    if (this.selectedImage != null) {
      const filePath = this.selectedImage.name;
      const fileRef = this.storage.ref(filePath);
      this.storage.upload(filePath, this.selectedImage).snapshotChanges().pipe(
        finalize(() => (fileRef.getDownloadURL().subscribe(url => {
          this.arrayPicture = url;
          this.formCreate.get('image')?.setValue(url);
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
