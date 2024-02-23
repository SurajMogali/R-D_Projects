// import ListGroup from "./components/ListGroup";

import Alert from "./components/Alert";
import Button from "./components/Button";

function App() {
  // let items = [
  //   "New York",
  //   "San Fransisco",
  //   "Tokyo",
  //   "Delhi",
  //   "London",
  //   "Paris",
  // ];
  return (
    //The passed elements and functions in props are accessed here
    // <div className="alert alert-primary">
    //   <ListGroup items={items} heading="Cities" />
    //   <Alert>
    //     Hello<span>World</span>
    //   </Alert>
    // </div>

    <div>
      <Button onClick={() => console.log("Clicked")}>My Button</Button>
    </div>
  );
}

export default App;
