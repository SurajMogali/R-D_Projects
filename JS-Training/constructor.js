class User {
    username;
    gender;
    email;


    constructor(name,gender,email){
        this.username=name;
        this.gender=gender;
        this.email=email;
    
    
    }
    
    
    display(){
        console.log(this.username,this.gender,this.email);
    }
}


var user1= new User("Raja","male","Raja@gmail.com")
var user2= new User("Suraj","male","Suraj@gmail.com")
var user3= new User("Tejasvi","male","Tejasvi@gmail.com")


user1.display();
user2.display();
user3.display();






