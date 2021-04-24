let humanScore = 0;
let computerScore = 0;
let currentRoundNumber = 1;

// Write your code below:

//generate target
const generateTarget = () => Math.floor(Math.random() * 9);

//get absolute number
const getAbsoluteDistance = numberIn => Math.abs(numberIn);

//compare guesses between human and computer
function compareGuesses(human, computer, target){
    if (human < 0 || human > 9){
        alert('Number is out of range. The point goes to the computer.');
        return false;
    }
    
    let humanDifference = getAbsoluteDistance(target-human);
    let computerDifference = getAbsoluteDistance(target-computer);

    if (humanDifference > computerDifference) return false;
    return true;
}



//add 1 point to winning side
function updateScore(winner){
    switch (winner){
        case 'human':
            humanScore++;
            break;
        case 'computer':
            computerScore++;
            break;
    }
}

//add round number
function advanceRound(){
    currentRoundNumber++;
}
