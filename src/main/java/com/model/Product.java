package com.model;

import java.io.BufferedOutputStream;
import javax.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Entity
@Table(name="Product")
public class Product 
{

	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Length(min=3, max=20)
	@NotEmpty(message="Product Name should not be empty")
	private String PrdName;
	
	@NotNull(message="Product Price should not be null")
	private float Price;
	private String Category;
	private int Warranty;
	private float Discount;
	private String ImgPath;
	
	transient private MultipartFile mpartFile;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrdName() {
		return PrdName;
	}
	public void setPrdName(String prdName) {
		PrdName = prdName;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public int getWarranty() {
		return Warranty;
	}
	public void setWarranty(int warranty) {
		Warranty = warranty;
	}
	
	public float getDiscount() {
		return Discount;
	}
	public void setDiscount(float discount) {
		Discount = discount;
	}
	public String getImgPath() {
		return ImgPath;
	}
	public void setImgPath(String imgPath) {
		ImgPath = imgPath;
	}
	public MultipartFile getMpartFile() {
		return mpartFile;
	}
	public void setMpartFile(MultipartFile mpartFile) {
		this.mpartFile = mpartFile;
	}
	
	public String getFilePath(String path1,String contextPath)
	{
		String fileName=null;
		if(!mpartFile.isEmpty())
		{
			try
			{
				fileName=mpartFile.getOriginalFilename();
				byte[] bytes = mpartFile.getBytes();
				String npath=path1+"\\resources\\"+fileName;
				BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(npath)));
				buffStream.write(bytes);
	            buffStream.close();
	            String dbfilename=contextPath+"/resources/"+fileName;
	            setImgPath(dbfilename);
	            return dbfilename;
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				return "fail";
			}
		}
		else
		{
			return "fail";
		}
	}

}


