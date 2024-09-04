// Spread Operator is used to copy the properties of one object to another object. The three dots(…) is used as Spread Operator



var obj1={
    username:"Rajesh Verma",
    city:"Delhi",
    id:101,
    gender:"male"

};


//Add the new value or update the existing value
var obj2={
    ...obj1,
    email:"Rajesh@gmail.com",
    city:"Pune",

};

console.log(obj2);

var arr1=[100,"Riya","female",90000,"Mumbai"];
var arr2=[...arr1,"Riya@gmail.com"];
console.log(arr2); 
