package hello.cipher.lea.crypto.mode;

import hello.cipher.lea.crypto.BlockCipher;
import hello.cipher.lea.crypto.BlockCipher.Mode;
import hello.cipher.lea.crypto.BlockCipherModeStream;

import static hello.cipher.lea.crypto.util.Ops.*;

// DONE: block vs buffer
public class OFBMode extends BlockCipherModeStream {

	private byte[] iv;
	private byte[] block;

	public OFBMode(BlockCipher cipher) {
		super(cipher);
	}

	@Override
	public String getAlgorithmName() {
		return engine.getAlgorithmName() + "/OFB";
	}

	@Override
	public void init(Mode mode, byte[] mk, byte[] iv) {
		this.mode = mode;
		engine.init(Mode.ENCRYPT, mk);

		this.iv = iv.clone();
		block = new byte[blocksize];
		reset();
	}

	@Override
	public void reset() {
		super.reset();
		System.arraycopy(iv, 0, block, 0, blocksize);
	}

	@Override
	protected int processBlock(byte[] in, int inOff, byte[] out, int outOff, int outlen) {
		int length = engine.processBlock(block, 0, block, 0);
		XOR(out, outOff, in, inOff, block, 0, outlen);

		return length;
	}
}
