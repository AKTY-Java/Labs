import './App.css';

function Home(prop) {
  return (
    <div>
        <h1>{prop.title}</h1>
        <h2>Welcome to the Home Page</h2>
        <p>{prop.description}</p>
    </div>
  );
}

export default Home;