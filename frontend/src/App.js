import logo from './logo.svg';
import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AllCards from './AllCards';
import AddCard from './AddCard';
import './App.css';


class App extends Component {

    render() {
		
		return (
			<table width = "100%">
			   <tr width = "100%">
			<div width = "100%">
				<Container fluid>					
					<h2>Credit Card System</h2>
				</Container>
			</div>
		      </tr>			
				<tr width = "100%">
					<div width = "100%">
						<Container fluid>					
							<h3>Add</h3>
							<AddCard />
						</Container>
					</div>
				</tr>
				<tr width = "100%">
					<div width = "100%">
						<Container fluid>					
							<h3>Existing Cards</h3>
							<AllCards />
						</Container>
					</div>			
				</tr>
			</table>			
        );
    }
}


export default App;
