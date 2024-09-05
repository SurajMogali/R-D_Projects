class Products{

    brandName="Apple";
    modelname="Macbook"
    price = 23000;
    rating =4.5;


    display(){
        console.log(this.brandName,this.modelname,this.price,this.rating);    }


    
    changePrice(newPrice){
        this.price=newPrice
        this.display();
    }


}


//Constructor declaration
var product1=new Products() 
var product2=new Products()

product1.display();

console.log(product2);

product2.brandName="Samsung"
product2.modelName="Galaxy S23";
product2.price=12500
product2.rating=4.3


console.log(product2);


