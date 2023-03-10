# Kindle Highlights

## MAIN OBJECTIVE
Showing desired number of random Kindle highlights daily based on the selected categories.

## BASIC USE CASE
The user can import Kindle highlights based on `My Clippings.txt` file. These highlights will be stored in the phone's memory. The user can add different categories and assign them to highlights. One highlight can be assigned to multiple categories. So the highlights can be grouped by categories and books they belong to. The user then selects the desired number of highlights he wants to see every day and categories/books they should be assigned to. Every day the application will show different randomly selected highlights based on user's settings.

## APP FEATURES
- importing Kindle Highlights based on `My Clippings.txt` file
- creating custom categories
- assigning highlights to categories
- displaying randomly selected highlights based on selected categories
- setting desired categories and number of highligths to be shown every day

## SCREENS
1. Home
- highlights for the day (list of items)

2. Settings
- number of highlights per day
- desired categories

3. Highlights browser
- list of highlights

4. Highlight details
- full highlight text
- categories
- possibility to assign the highlight to categories

5. Import highlights
- from file from storage
- filtering out bookmarks
- combining highlights with notes
- detecting and removing possible duplicates 
- reviewing newly added highlights before accepting (?)

6. Categories/Books + add new category
- list of categories
- category details (how many highlights assigned etc.)
- possibility to add new category (bottom sheet?)

## MODELS

### Highlight
- book - Book
- categories - List(Category)
- content - string
- date - date or string
- id - string
- note - text (optional)

### Book
- author - string
- id - string
- title - string

### Category
- date (?) - date
- id - string
- name - string