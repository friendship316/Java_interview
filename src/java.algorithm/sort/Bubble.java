package sort;

/**
 * @create: 2021-03-30 22:53
 **/
public class Bubble {

    public static void main(String[] args) {
        int[] arr = new int[]{3,6,9,2,7,5,4};
        sortS2L(arr);
        for (int anArr : arr) {
            System.out.println(anArr);
        }
    }


    private static void sortS2L(int[] arr){
        int count = 0;
        for (int i=0;i<arr.length;i++){
            boolean end = false;
            for (int j=0;j<arr.length-1-i;j++){
                if (arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    count++;
                    end = true;
                }
            }
            if (!end){
               break;
            }
        }
        System.out.println("次数："+count);
    }

}
