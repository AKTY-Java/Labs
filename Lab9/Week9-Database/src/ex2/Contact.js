import './App.css';

function Contact(prop) {
  return (
    <div>
      <h1>{prop.title}</h1>
      <h2>Contact us</h2>
      <p>{prop.description}</p>
    </div>
  );
}

export default Contact;