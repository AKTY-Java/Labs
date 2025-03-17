import './App.css';

function About(prop) {
  return (
    <div>
      <h1>{prop.title}</h1>
      <h2>About us</h2>
      <p>{prop.description}</p>
    </div>
  );
}

export default About;