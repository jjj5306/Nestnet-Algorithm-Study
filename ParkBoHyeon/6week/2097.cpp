#include <iostream>
#include <cmath>
using namespace std;

int main() {
    int N;
    cin >> N;
    
    if (N <= 4) {
        cout << 4 << endl;
        return 0;
    }
    
    int side = sqrt(N - 1) + 1;
    int result = side * 4 - 4;
    
    cout << result << endl;
    
    return 0;
}