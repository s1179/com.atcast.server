package com.atmedios.atcast.controller;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

import org.xiph.speex.AudioFileWriter;
import org.xiph.speex.OggSpeexWriter;
import org.xiph.speex.SpeexDecoder;
import org.xiph.speex.SpeexEncoder;

public class AudioController {

		private AudioFileWriter filesave;
		public AudioController(){
			
		}
	    public void record(String filename) throws Exception {
	    int sample_rate = 16000;
	    int sample_size = 16;
	    int channels = 1;
	    AudioFormat format = new AudioFormat(sample_rate, sample_size,
	        channels, true, false);
	    TargetDataLine line_in;
	    DataLine.Info info_in = new DataLine.Info(TargetDataLine.class, format);
	    try {
	        line_in = (TargetDataLine) AudioSystem.getLine(info_in);
	        line_in.open(format);
	    } catch (LineUnavailableException ex) {
	        ex.printStackTrace();
	        return;
	    }
	    DataLine.Info info_out = new DataLine.Info(SourceDataLine.class, format);
	    SourceDataLine line_out;
	    try {
	        line_out = (SourceDataLine) AudioSystem.getLine(info_out);
	        line_out.open(format);
	    } catch (LineUnavailableException ex) {
	        ex.printStackTrace();
	        return;
	    }
	    //Inicializar codec
	    SpeexEncoder encoder = new SpeexEncoder();
	    SpeexDecoder decoder = new SpeexDecoder(); //para monitorizar
	    encoder.init(1, 10, sample_rate, channels);
	    decoder.init(1, sample_rate, channels, false);
	    
	    //Escribir archivo
	    filesave = new OggSpeexWriter(1, 16000, 1, 1, false);
	    filesave.open(filename);
	    filesave.writeHeader(filename);
	    		//"Generado por ATCast");
	    
	    int raw_block_size = encoder.getFrameSize() * channels
	        * (sample_size / 8);
	    byte[] buffer = new byte[raw_block_size * 2];
	    line_in.start();
	    line_out.start();
	    while (true) {
	        /*int read = */line_in.read(buffer, 0, raw_block_size);
	        if (!encoder.processData(buffer, 0, raw_block_size)) {
	        System.err.println("Could not encode data!");
	        break;
	        }
	        int encoded = encoder.getProcessedData(buffer, 0);
	        /*System.out.println(encoded
	            + " bytes resulted as a result of encoding " + read
	            + " raw bytes.");*/
	        byte[] encoded_data = new byte[encoded];
	        System.arraycopy(buffer, 0, encoded_data, 0, encoded); //se pasa a la entrada de nuevo
	        filesave.writePacket(encoded_data, 0, 1); //se pasa al archivo
	        
	        
	        decoder.processData(encoded_data, 0, encoded);
	        byte[] decoded_data = new byte[decoder.getProcessedDataByteSize()];
	        int decoded = decoder.getProcessedData(decoded_data, 0);
	        /*System.out.println(decoded
	            + " bytes resulted as a result of decoding " + encoded
	            + " encoded bytes.");*/
	        line_out.write(decoded_data, 0, decoded);
	    }filesave.close();
	    }
	}


