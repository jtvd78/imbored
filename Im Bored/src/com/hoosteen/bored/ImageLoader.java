package com.hoosteen.bored;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static Image loadImage(String s){
		try {
			return ImageIO.read(ImageLoader.class.getResource(s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}