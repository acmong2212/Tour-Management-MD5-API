import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Tour} from "../tour-management/model/tour";

@Injectable({
  providedIn: 'root'
})
export class TourService {

  constructor(private http: HttpClient) {
  }

  findAll(): Observable<any> {
    return this.http.get<any>("http://localhost:8080/tour");
  }

  findTourById(id: number): Observable<any> {
    return this.http.get<any>("http://localhost:8080/tour/" + id);
  }

  createTour(tour: Tour): Observable<any> {
    return this.http.post("http://localhost:8080/tour", tour);
  }

  updateTour(tour: Tour): Observable<any> {
    return this.http.put("http://localhost:8080/tour/" + tour.id, tour);
  }

  deleteTour(id: number): Observable<any> {
    return this.http.delete<any>("http://localhost:8080/tour/" + id)
  }

  findTourByNameContaining(name: string): Observable<any> {
    return this.http.get("http://localhost:8080/tour/search/" + name)
  }
}
