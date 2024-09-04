function findNumberIsEven(num){
    if(num%2==0)
    {
        console.log("Its an even number")

    }

}

findNumberIsEven(6);

function firstTenNumber()
{
    var x=1;

    while(x<=10){
        console.log(x);
        x++;

    }

}

firstTenNumber();


var flag =false;
function isPrime(num){

    for(var i=2;i<=num/2;i++)
    {
        if(num%i==0){
        flag =true;
        }


    }

    if(!flag)
        {
            console.log("It is prime number");
    
        }else
        {
            console.log("It is not a prime number");
    
        }
}




isPrime(21);



