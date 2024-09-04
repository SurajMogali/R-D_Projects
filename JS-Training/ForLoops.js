//for in implementation - Used to iterate through the objectkeys

var obj={
    name:"Darshan",
    gender: "male",
    id:101,
    city:"Delhi"
};

for(var x in obj){
    console.log(x,":",obj[x]);

}



//Iterate through the array using forof
var arr=[10,20,34,89,87,32];

for(var a of arr){
    console.log(a);
}

