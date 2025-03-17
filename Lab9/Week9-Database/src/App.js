import './App.css';
import Home from './Home.js';
import About from './About.js';
import Contact from './Contact.js';
import EngineeringTopics from './EngineeringTopics.js';

function App() {
  var currentYear = (new Date()).getFullYear();
  var isLoggedIn = true;
  return (
    <div>
      <h1>ENSF-381: Full Stack Web Development</h1>
      <p>React Components</p>
      <p>{currentYear}</p>
      {isLoggedIn ? <p>Welcome back!</p> : <p>Please log in.</p>}
    
    <Home title='Home Page' description='Welcome to our website.'></Home>
    <About title='About Us' description='We are passionate about delivering quality experiences.'></About>
    <Contact title='Contact Us' description='Feel free to reach out to us via email or phone.'></Contact>
    <EngineeringTopics/>
    </div>
  );
}

export default App;
