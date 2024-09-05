var obj1={
    c:100

}

var obj2={
    c:300
}


function fnAdd(x,y){

    console.log(x+y+this.c);

}

var fnAdd = fnAdd.bind(obj1);
fnAdd(10,10)  //120


//The above can also be written as 
var fnAdd=fnAdd.bind(obj1,50,50);
fnAdd();


// Call, Apply, Bind

// They are all predefined functions which is used to change the reference of the this keyword.
// Call  - There is no restriction, how much arguments it will take
// Apply- It will take only two arguments in the array format
// Bind – Same like call but it will return function



