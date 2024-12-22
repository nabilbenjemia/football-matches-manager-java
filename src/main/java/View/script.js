// JavaScript to handle navigation between views
const registerButton = document.getElementById('registerButton');
const backButton = document.getElementById('backButton');
const menuView = document.getElementById('menu');
const registerView = document.getElementById('registerView');
const viewAllMatchesButton = document.getElementById('viewAllMatchesButton');
const viewAllMatchesView = document.getElementById('viewAllMatchesView');
const backToMenuButton = document.getElementById('backToMenuButton');
const matchesContainer = document.getElementById('matchesContainer');
const upcomingMatchesContainer = document.getElementById('upcomingMatchesContainer');
const oldMatchesContainer = document.getElementById('oldMatchesContainer');

const viewUpcomingMatchesButton = document.getElementById('viewUpcomingMatchesButton');
const viewOldMatchesButton = document.getElementById('viewOldMatchesButton');
const viewOldMatchesView = document.getElementById('viewOldMatchesView');
const viewUpcomingMatchesView = document.getElementById('viewUpcomingMatchesView');

// Show the registration form when "Register as Administrator" is clicked
registerButton.addEventListener('click', () => {
    menuView.classList.remove('active');
    registerView.classList.add('active');
});

// Go back to the main menu when "Back" is clicked
backButton.addEventListener('click', () => {
    registerView.classList.remove('active');
    menuView.classList.add('active');
});

// Handle form submission
const registerForm = document.getElementById('registerForm');
registerForm.addEventListener('submit', (e) => {
    menuView.classList.remove('active');
    registerView.classList.add('active');
    e.preventDefault(); // Prevent actual form submission
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    alert(`Username: ${username}\nPassword: ${password}`);
});

viewAllMatchesButton.addEventListener('click', async () => {
        menuView.classList.remove('active');
        viewAllMatchesView.classList.add('active');
        // Fetch matches from the backend
        fetchMatches('http://localhost:8080/matches/all', 'matchesContainer');
    }
);

/////////////////////
viewUpcomingMatchesButton.addEventListener('click', async () => {
        menuView.classList.remove('active');
        viewUpcomingMatchesView.classList.add('active');
        // Fetch matches from the backend
        fetchMatches('http://localhost:8080/matches/upcoming', 'upcomingMatchesContainer');
    }

);

///////////////////////////
viewOldMatchesButton.addEventListener('click', async () => {
        menuView.classList.remove('active');
        viewOldMatchesView.classList.add('active');
        // Fetch matches from the backend
        fetchMatches('http://localhost:8080/matches/old', 'oldMatchesContainer');
    }

);


async function fetchMatches(apiEndpoint, containerClass) {
    const matchesContainer = document.querySelectorAll(`.${containerClass}`);
    matchesContainer.forEach(container => container.innerHTML = ''); // Clear the container(s)

    if (!matchesContainer) {
        console.error('matchesContainer element not found!');
        return;
    }

    try {
        console.log("Sending request to backend...");

        const response = await fetch(apiEndpoint);
        console.log("Response received:", response.status);

        // Check if response is OK
        if (!response.ok) {
            matchesContainer.innerHTML = `<p>Failed to fetch matches. Status code: ${response.status}</p>`;
            throw new Error('Failed to fetch matches');
        } else {
            matchesContainer.innerHTML = `<p>Status code: ${response.status}</p>`;
        }

        const matches = await response.json();

        // Clear the container and display matches
        matchesContainer.innerHTML = ''; // Clear any existing data
        matches.forEach(match => {
            const matchDiv = document.createElement('div');
            matchDiv.innerHTML = `
                <p><strong>Opponent:</strong> ${match.opponent}</p>
                <p><strong>Competition:</strong> ${match.competition}</p>
                <p><strong>Match Day:</strong> ${match.matchDay}</p>
                <p><strong>Home:</strong> ${match.home ? 'Yes' : 'No'}</p>
                <p><strong>Score:</strong> ${match.scoredGoals} - ${match.opponentGoals}</p>
                <hr>
            `;
            matchesContainer.appendChild(matchDiv);
        });
    } catch (error) {
        console.error("Error fetching matches:", error);
        matchesContainer.innerHTML = '<p>Could not load matches. Please try again later.</p>';
    }

}

backToMenuButton.addEventListener('click', () => {
    registerView.classList.remove('active');
    viewUpcomingMatchesButton.classList.remove('active');
    viewOldMatchesView.classList.remove('active');
    viewAllMatchesView.classList.remove('active');
    menuView.classList.remove('active');
    menuView.classList.add('active');
});
