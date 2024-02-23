import { Fragment, useState } from "react";
import { MouseEvent } from "react";

interface Props {
  items: string[];
  heading: string;
}
//{items:[] ,heading:string}

function ListGroup({ items, heading }: Props) {
  const [selectedIndex, setSelectedIndex] = useState(-1);
  //   arr[0]; //variable (selectedIndex)
  //   arr[1]; //updater function

  //Event hanlder
  // const handleClick = (event: MouseEvent) => console.log(event);

  //items = [];

  const getMessage = () => {
    return;
  };

  return (
    <>
      <h1>{heading}</h1>
      {items.length === 0 ? <p>No item Found</p> : null}

      <ul className="list-group">
        {items.map((item, index) => (
          <li
            className={
              selectedIndex === index
                ? "list-group-item active"
                : "list-group-item"
            }
            key={item} // This assigns a unique key to the list item.
            onClick={() => {
              setSelectedIndex(index);
            }}
          >
            {item}
          </li>
        ))}
      </ul>
    </>
  );
}

export default ListGroup;
