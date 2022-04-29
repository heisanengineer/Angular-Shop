## Rotations

In this labwork are asked to write a GUI application using AWT. It is highly advised that you build your work and knowledge upon labwork 7. Also you may want to revisit the labwork about rectangles so that you can review rotations. You can watch the small video demo on WebOnline to get a better understanding. You should also use your knowledge that you gained during theoretical lectures, with this labwork we will be done with transformations of 2D objects and later on we will start working on 3D objects and animations. You are expected to;

- Have a colored background of your liking. I have used Color.getHSBColor(0.4f, 0.3f, 0.9f).
- Two lines that divide the frame into 4 equal parts, that are x and y axes.
- A rectangle that is filled with a color. (You can use fillPolygon() function)
- Add a control panel.
- Add 3 buttons to the panel called “Left Bottom - Clockwise", “"Origin - Clockwise” and "Origin - C.Clockwise”.
- Menubar of this program should have an item called “Set Default”which will reset the values of x and y to the values they had initially.
- Modify the action listener according to the buttons and menu item.
- Set the title to “Rotations” in the constructor and add your WindowListener.
- Modify your paint method to draw 1 rectangle and 2 lines with different colors.
- The main method should create and display an instance of Rotations().
- The frame should be set to the size 800 by 800.
- You can use a grid layout which is pretty straightforward.
- Submit only one file called Rotations.java.
 
 
Rotations about origin Clockwise rotation about left-bottom corner For all reflections you should rotate the rectangle exactly 90 degrees.

During rotations you should keep in mind that the rectangle we are using has different width and height.

For rotation about origin you should implement both clockwise and counter-clockwise. However, for rotation around left-bottom corner, which we have already implemented on a previous labwork, only implementing clockwise is enough.

You may want to use states for the rotated versions of the rectangle, keeping track of the current version and changing the state to next can be easier. 

You can assume that only 1 kind of rotation (either around origin or corner) will be tested at a time. You may add extra attributes for this purpose.

You should keep in mind that the coordinate system used in AWT is totally different from what is described as x and y above, you should adapt your code so that it replicates the behavior of such coordinate system. In AWT the upper left corner is (0,0) and the coordinates increase in the right and down directions, so the bottom right corner is (800,800) for a 800x800 frame.

For more information about the transformations please refer to the lecture slides on WebOnline.

<img width="822" alt="Ekran Resmi 2022-04-29 23 04 24" src="https://user-images.githubusercontent.com/93338158/166061872-8d9e7cf8-ab93-4b93-97ca-95f59844edb6.png">

