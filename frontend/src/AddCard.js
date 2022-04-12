import logo from './logo.svg';
import React, { Component, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import './App.css';

	
function AddCard() {
  const [cardHolderName, setName] = useState("");
  const [cardNumber, setCardNumber] = useState("");
  const [cardLimit, setLimit] = useState("");
  const [message, setMessage] = useState("");

  let handleSubmit = async (e) => {
    e.preventDefault();
    try {
		console.log(JSON.stringify({
          'cardHolderName': cardHolderName,
          'cardNumber': cardNumber,
          'cardLimit': cardLimit
        }))
      let res = await fetch("http://localhost:8080/v1/creditcards/add", {
        method: "POST",
		headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          'cardHolderName': cardHolderName,
          'cardNumber': cardNumber,
          'cardLimit': cardLimit
        }),
      });
      
	  console.log(res.status)
      if (res.status === 201) { 
		window.location.reload();
		
      } else {
		let resJson = await res.json();
			  console.log(resJson)
        setMessage(resJson.messages);
      }
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div className="App">
      <form onSubmit={handleSubmit}>
        <table width="100%">
			<tr align="left"> <td>Name</td> </tr>
			<tr align="left">	
				<td align="left">
					<input type="text" class="textbox"
						   value={cardHolderName}
						   onChange={(e) => setName(e.target.value)}
					/>
				</td>
			</tr>
			<tr align="left"> <td>Card Number</td> </tr>
			<tr align="left">
				<td align="left">
					<input type="text" class="textbox"
						   value={cardNumber}
						   onChange={(e) => setCardNumber(e.target.value)}
					/>
				</td>
			</tr>
			<tr align="left"> <td>Limit</td> </tr> 
			<tr align="left">
				
				<td align="left">
					<input type="text" class="textbox"
						   value={cardLimit}
						   onChange={(e) => setLimit(e.target.value)}
					/>
				</td>
			</tr>
			<tr> <td> </td> </tr>
			<tr align="left"><button type="submit" class="button">Add</button></tr>
			<tr align="left"><div className="message">{message ? <p>{message}</p> : null}</div></tr>
		</table>      
      </form>
    </div>
  );
}


export default AddCard;
