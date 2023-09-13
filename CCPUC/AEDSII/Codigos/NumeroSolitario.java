class NumeroSolitario{
	public static void main(String[] args){
		int n;
		n=MyIO.readInt();
		boolean respPar=false;
		while(n!=0){
			int[] array=new int[n];
			for(int i=0;i<n;i++){
				array[i]=MyIO.readInt();
			}
			for(int i=0;i<n;i++){
				respPar=false;
				for(int j=0;j<n;j++){
					if(!(j==i)){
						if(array[i]==array[j])
							respPar=true;
					}
				}
				if(!respPar){
					MyIO.print(array[i]);
				}
			}
			n=MyIO.readInt();
		}
	}
}
