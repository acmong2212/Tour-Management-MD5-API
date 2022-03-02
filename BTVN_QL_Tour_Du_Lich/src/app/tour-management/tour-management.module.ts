import {TourListComponent} from "./tour-list/tour-list.component";
import {TourUpdateComponent} from "./tour-update/tour-update.component";
import {TourCreateComponent} from "./tour-create/tour-create.component";
import {HeaderComponent} from "./header/header.component";
import {TourDetailComponent} from "./tour-detail/tour-detail.component";
import {TourDeleteComponent} from "./tour-delete/tour-delete.component";
import {TourManagementComponent} from "./tour-management.component";
import {CommonModule} from "@angular/common";
import {TourRoutingModule} from "./tour-routing.module";
import {FormsModule, ReactiveFormsModule } from "@angular/forms";
import {RouterModule} from "@angular/router";
import {NgModule} from "@angular/core";

@NgModule({
  declarations: [
    HeaderComponent,
    TourCreateComponent,
    TourUpdateComponent,
    TourDeleteComponent,
    TourDetailComponent,
    TourManagementComponent,
    TourListComponent
  ],
  imports: [
    CommonModule,
    TourRoutingModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [TourManagementComponent]
})
export class TourManagementModule { }
