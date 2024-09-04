// It is a technique to access scope of outer function inside the inner function
// Or It is a technique to define a function inside another function






function Outer(){
    var city="Delhi";
    console.log("Outer Function",city);

    function Inner(x,y){
        console.log(x+y)
        console.log("Inner Function",city)
        }

    Inner(10,20);

}


Outer();
// var fninner=Outer();
// fninner(5,5);