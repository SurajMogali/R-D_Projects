//It is technique in JavaScript to unpack properties or elements from the object or Array.

var obj={
    username:"Rajesh Verma",
    city:"Delhi",
    id:101,
    gender:"male"
};

//Object Destructuring
var {city,gender,id}=obj
//100 places

console.log(city,gender,id);



//Normal way to access the element of the array
var productDetails=["IPhone","Samsung","Moto","Vivo","Oppo","Nokia" ];
console.log(productDetails[3]);


//Array Destructuring
var[element1,element2,element3] =productDetails;
console.log(element1,element2,element3);