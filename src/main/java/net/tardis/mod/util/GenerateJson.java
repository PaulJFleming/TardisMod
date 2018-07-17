package net.tardis.mod.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumFacing;
import net.tardis.mod.Tardis;
import net.tardis.mod.util.helpers.Helper;

public class GenerateJson {
	
	
	public static void generateFacingBlockstate(String name) {
		
		try{
			File f = new File(Minecraft.getMinecraft().mcDataDir.getAbsolutePath() + "\\..\\src\\main\\resources\\assets\\tardis\\blockstates\\" + name + ".json");
			if(!f.exists())f.createNewFile();
			Writer fw = new OutputStreamWriter(new FileOutputStream(f), "UTF-8"); 
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonWriter jw = gson.newJsonWriter(fw);

			String model = Tardis.MODID + ":" + name;
			jw.beginObject();
			jw.name("forge_marker").value(1);
			jw.name("defaults");
			jw.beginObject();
			jw.name("model").value(model);
			jw.endObject();
			jw.name("variants");
			jw.beginObject();
			for(EnumFacing face : EnumFacing.HORIZONTALS) {
				jw.name("facing=" + face.getName2());
				jw.beginObject();
				jw.name("model").value(model);
				jw.name("y").value(Helper.getAngleFromFacing(face));
				jw.endObject();
			}
			jw.name("inventory");
			writeJSONVarient(jw, model);
			
			jw.name("normal");
			writeJSONVarient(jw, model);
			
			jw.endObject();
			jw.endObject();
			
			fw.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void genCubeAll(Block b) {
		try {
			File f = new File(Minecraft.getMinecraft().mcDataDir.getAbsolutePath() + "\\..\\src\\main\\resources\\assets\\tardis\\blockstates\\");
			if(!f.exists())f.createNewFile();
			Writer writer = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonWriter jw = gson.newJsonWriter(writer);
			
			jw.beginObject();
			jw.name("cat").value(200);
			jw.endObject();
			
			writer.close();
		}
		catch(Exception e){}
	}
	
	public static void writeJSONVarient(JsonWriter jw, String model) throws IOException {
		jw.beginObject();
		jw.name("model").value(model);
		jw.endObject();
	}
}