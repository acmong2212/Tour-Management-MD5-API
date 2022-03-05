import {Category} from "./category";

export class Tour{
  id!: number;
  name!: string;
  image!: string;
  price!: number;
  description!: string
  category!: Category

  constructor(id: number, name: string,image: string ,price: number, description: string, category: Category) {
    this.id = id;
    this.name = name;
    this.image = image;
    this.price = price;
    this.description = description;
    this.category = category;
  }
}
