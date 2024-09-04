// function fn(){
//     return "I am Returning a String"
// }

var myArrowfn = () => "I am returning a String"; 
console.log(myArrowfn());  //Call that variable as a function


//Here it will be mapped to that object only.
var obj1={
    username:"Raj",
    city:"Bangalore",
    print:function(){
    console.log(this.username)  //obj1.username
    }

  

}

obj1.print();

//Here this will be  mppaing to global object which u will get as undefined 
var obj2={
    username:"Raj",
    city:"Bangalore",
    print:()=>{
    console.log(this.username)  //obj2.username
    }

  

}

obj2.print();