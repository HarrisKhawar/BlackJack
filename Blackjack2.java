//Created by Harris Mahmood Khawar on 25/10/2017.

import java.util.Scanner;

public class Blackjack2 {
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        int indexdeck = 0;
        int money = 100;
        int wage = 0;
        int usertotal = 0;
        int dealertotal = 0;
        int card, hiddencard;
        int check1=1, check2 = 1, check3 = 1;

        int[] deck = new int[52];
        initializeDeck(deck);
        shuffle(deck);
//Start
        while(check3 == 1) {
            if (indexdeck>31){
                initializeDeck(deck);
                shuffle(deck);
                indexdeck = 0;
                System.out.println("New Deck will be used.\n");
            }
            while (check1 == 1) {
                System.out.println("You have $" + money + ". How much do you want to bet?");

//Set Wage
                wage = keyboard.nextInt();
                if (wage > money || wage <= 0) {
                    System.out.println("Please enter an amount greater than zero and lesser than your money.");
                } else {
                    check1 = 2;
                }
            }
            check1 = 1;

            money = money - wage;
            System.out.println("Bet Placed!");

//User Draws
            System.out.println("\nYour Cards:");

            card = draw(deck, indexdeck);
            displaycard(card);
            usertotal = usertotal + value(card);
            indexdeck = indexdeck + 1;

            card = draw(deck, indexdeck);
            displaycard(card);
            usertotal = usertotal + value(card);
            indexdeck = indexdeck + 1;

            System.out.println("\nYour total is " + usertotal);

//Dealer Draws
            System.out.println("\nMy Cards:");

            card = draw(deck, indexdeck);
            displaycard(card);
            dealertotal = dealertotal + value(card);
            indexdeck = indexdeck + 1;

            hiddencard = draw(deck, indexdeck);
            System.out.println("HIDDEN");
            dealertotal = dealertotal + value(hiddencard);
            indexdeck = indexdeck + 1;

//Hit or Stay
            while (check2 == 1) {
                if (usertotal > 21) {
                    System.out.println("\nYou have Busted!");
                    break;
                }
                System.out.println("\nChoose an option:\n1) Hit  2) Stay");
                check2 = keyboard.nextInt();
                if (check2 == 1) {
                    System.out.println("Your new card:");
                    card = draw(deck, indexdeck);
                    displaycard(card);
                    usertotal = usertotal + value(card);
                    indexdeck = indexdeck + 1;

                    System.out.println("\nYour total is " + usertotal);
                } else if (check2 == 2) {
                    while (dealertotal < 17) {
                        card = draw(deck, indexdeck);
                        dealertotal = dealertotal + value(card);
                        indexdeck = indexdeck + 1;
                    }
                    System.out.println("My hidden card: ");
                    displaycard(hiddencard);
                }
            } check2 = 1;

//Game Result
            System.out.println("\nYour total is " + usertotal);
            System.out.println("My total is " + dealertotal);
            if (usertotal > 21) {
                money = money +lose(money, wage);
            } else if (dealertotal > 21) {
                money = money +win(money, wage);
            } else if (dealertotal == 21) {
                money = money + lose(money, wage);
            } else if (usertotal == 21) {
                money = money + win(money, wage);
            } else if (usertotal > dealertotal) {
                money = money + win(money, wage);
            } else if (usertotal < dealertotal) {
                money = money +lose(money, wage);
            } else if (usertotal == dealertotal) {
                money = money + tie(money, wage);
            }

//Continue
            if(money == 0){
                System.out.println("GAME OVER - You lost all your money!");
                check3 = 0;
            } else {
                System.out.println("\nChoose an option:\n1)Play Next Hand  2)End Game");
                check3 = keyboard.nextInt();
            }
//Reset
            usertotal = 0;
            dealertotal = 0;
            wage = 0;
        }
    }
//Initialize Deck
    public static void initializeDeck (int[] deck) {
        for (int i = 0; i < 52; i = i + 1) {
            deck[i] = i;
        }
    }

//Get Rank
    public static String getRank (int card) {
        String[] ranks = new String[] {"Ace",
                "Two",
                "Three",
                "Four",
                "Five",
                "Six",
                "Seven",
                "Eight",
                "Nine",
                "Ten",
                "Jack",
                "Queen",
                "King"};
        return ranks[card % 13];
    }

//Get Suit
    public static String getSuit (int card) {
        String[] suits = new String[] {"spades",
                "hearts",
                "clubs",
                "diamonds"};
        return suits[card / 13];
    }

//Shuffle
    public static void shuffle (int [] shuffle){
        for (int i = 0; i<shuffle.length; i++){
            int a = (int) (Math.random()*(52));
            int temp = shuffle[i];
            shuffle[i] = shuffle[a];
            shuffle[a] = temp;
        }
    }
//Draw Card
    public static int draw(int[] deck, int indexdeck){
        int newcard = deck[indexdeck];
        return newcard;
    }

//Display Card
    public static void displaycard(int card){
        System.out.println(getRank(card)+" of "+getSuit(card));
    }

//Display all cards
    public static void displayall(int [] deck){
        for(int i=0;i<deck.length;i++){
            System.out.print(i+" ");
            displaycard(deck[i]);
        }
    }

//Value
    public static int value(int card){
        int value=0;

        if((card%13)==0){
            value = value +10+1;

        } else if ((card%13)<10){
            value = value + (card%13)+1;

        } else if ((card%13)>9){
            value = value+ 10;
        }
        return value;
    }
//Game Lost
    public static int lose(int money, int wage){
        wage = 0;
        System.out.println("You have lost this hand!");
        return wage;
    }

//Game Won
    public static int win(int money, int wage){
        wage = wage*2;
        System.out.println("YOU HAVE WON!");
        return wage;
    }

//Game Tied
    public static int tie(int money, int wage){
        System.out.println("The Game has Tied.");
        return wage;
    }
}
